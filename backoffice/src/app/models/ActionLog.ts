export class ActionLog
{
  id!: number;
  action!: string;
  entityName!: string;
  entityId!: number;
  userid !: null;
  ipAddress!: string;
  httpMethod!: string;
  requestUri!: string;
  timestamp!: Date;
}
