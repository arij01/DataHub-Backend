import { Datasets } from "./Datasets";

export class Glossaires {
  id!: number;
  terme!: string;
  definition!: string;
  datasetAssocie!: string;
  utilisation!: string;
  dataset!: Datasets;
}
