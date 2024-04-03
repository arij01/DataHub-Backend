import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import * as d3 from 'd3';
import { ActionLog } from '../../models/ActionLog';
import { ActionLogService } from 'src/app/services/action-log.service';

@Component({
  selector: 'app-data-flow-diagram',
  templateUrl: './data-flow-diagram.component.html',
  styleUrls: ['./data-flow-diagram.component.css']
})
export class DataFlowDiagramComponent implements OnInit {
  @ViewChild('dataFlowDiagram', { static: true }) private dataFlowDiagram!: ElementRef;
  private data: { nodes: any[], links: any[] } = { nodes: [], links: [] };

  constructor(private actionLogService: ActionLogService) {}

  ngOnInit(): void {
    this.fetchData();
  }

  private fetchData(): void {
    this.actionLogService.getAllActionLogs().subscribe({
      next: (logs: ActionLog[]) => {
        const entities = new Set<string>();
        const relationships: { source: string, target: string, action: string }[] = [];
        logs.forEach(log => {
          entities.add(log.entityName);
          const relationship = { source: 'ActionLog', target: log.entityName, action: log.action };
          if (!this.isRelationshipExists(relationship, relationships)) {
            relationships.push(relationship);
          }
          entities.add('ActionLog');
        });

        this.data.nodes = Array.from(entities).map(entity => ({ id: entity, type: 'entity', r: 15 }));
        this.data.links = relationships;

        this.createDataFlowDiagram();
      },
      error: (error) => console.error(error),
      complete: () => console.log('Action logs fetched')
    });
  }

  private isRelationshipExists(relationship: { source: string, target: string, action: string }, relationships: any[]): boolean {
    return relationships.some(rel => rel.source === relationship.source && rel.target === relationship.target && rel.action === relationship.action);
  }

  private createDataFlowDiagram(): void {
    const svg = d3.select(this.dataFlowDiagram.nativeElement).append('svg')
      .attr('width', '100%')
      .attr('height', '100%')
      .attr('viewBox', '0 0 600 400')
      .attr('preserveAspectRatio', 'xMidYMid meet');

    this.data.nodes.forEach(d => {
      d.x = Math.random() * 600;
      d.y = Math.random() * 400;
    });

    const link = svg.selectAll('.link')
      .data(this.data.links)
      .enter().append('line')
      .attr('class', 'link')
      .attr('stroke', 'black');

    const node = svg.selectAll('.node')
      .data(this.data.nodes)
      .enter().append('g')
      .attr('class', 'node');

    node.append('rect')
      .attr('width', 100)
      .attr('height', 20)
      .attr('fill', '#ADD8E6');

    node.append('text')
      .attr('dy', '.35em')
      .text(d => d.id);

    const simulation = d3.forceSimulation<any>(this.data.nodes)
      .force('link', d3.forceLink<any, any>(this.data.links).id(d => d.id).distance(150))
      .force('charge', d3.forceManyBody().strength(-500))
      .force('center', d3.forceCenter(300, 200))
      .force('collision', d3.forceCollide().radius(d => (d as any).r + 10))
      .on('tick', () => {
        link.attr('x1', d => (d.source as any).x)
          .attr('y1', d => (d.source as any).y)
          .attr('x2', d => (d.target as any).x)
          .attr('y2', d => (d.target as any).y);

        node.attr('transform', d => `translate(${d.x}, ${d.y})`);
      });
  }
  addDatasetToDiagram(nom: string) {
    // Ajouter le dataset au tableau de données du diagramme
    this.data.nodes.push({ id: nom, type: 'entity', r: 15 });

    // Ajouter le lien entre le dataset et l'entité correspondante
    this.data.links.push({ source: 'ActionLog', target: nom, action: 'created' });

    // Mettre à jour le diagramme
    this.updateDiagram();
  }
  removeDatasetFromDiagram(nom: string) {
    // Supprimer le dataset du tableau de données du diagramme
    this.data.nodes = this.data.nodes.filter(node => node.id !== nom);

    // Supprimer les liens liés au dataset du tableau de liens du diagramme
    this.data.links = this.data.links.filter(link => link.target !== nom);

    // Mettre à jour le diagramme
    this.updateDiagram();
  }

  private updateDiagram(): void {
    // Mettre à jour le diagramme en fonction des nouvelles données et liens
    // Cette fonction devrait réinitialiser le diagramme et le redessiner avec les nouvelles données et liens
  }
}
