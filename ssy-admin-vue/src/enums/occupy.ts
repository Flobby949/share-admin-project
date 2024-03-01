export enum AuditResult {
  NONE = '待审核',
  PASS = '审核通过',
  FAIL = '审核不通过'
}

export enum AuditResultTag {
  NONE = 'danger',
  PASS = 'success',
  FAIL = 'info'
}

export enum OccupyType {
  ALL = '全占用',
  LEFT = '占用左',
  RIGHT = '占用右'
}

export enum OccupyTypeTag {
  ALL = 'danger',
  LEFT = 'warning',
  RIGHT = 'warning'
}
