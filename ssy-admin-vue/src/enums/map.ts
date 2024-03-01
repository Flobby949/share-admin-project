export enum RoadType {
  LINE = '道路',
  POINT = '路口',
  ENTRANCE = '出入口'
}
export enum RoadTypeTag {
  LINE = '',
  POINT = 'success',
  ENTRANCE = 'warning'
}

export enum RoadIsTwoWay {
  YES = '双向车道',
  NO = '单向车道'
}

export enum RoadIsTwoWayTag {
  YES = '',
  NO = 'success'
}

// 拥堵级别翻译
export enum RoadCongestion {
  CONGESTION0 = '通畅',
  CONGESTION1 = '缓行',
  CONGESTION2 = '拥堵',
  CONGESTION3 = '非常拥堵'
}

// 拥堵级别标签类别
export enum RoadCongestionTag {
  CONGESTION0 = 'success',
  CONGESTION1 = 'info',
  CONGESTION2 = 'warning',
  CONGESTION3 = 'danger'
}

// 拥堵级别颜色
export enum RoadCongestionColor {
  CONGESTION0 = '#b4ebaf',
  CONGESTION1 = '#ED8A2E',
  CONGESTION2 = '#FC4E22',
  CONGESTION3 = '#FF0000'
}

// 道路状态翻译
export enum RoadStatus {
  NORMAL = '正常',
  CLOSE = '关闭',
  ALL = '全占用',
  LEFT = '占用左',
  RIGHT = '占用右'
}

// 道路状态标签
export enum RoadStatusTag {
  NORMAL = 'success',
  CLOSE = 'info',
  ALL = 'danger',
  LEFT = 'warning',
  RIGHT = 'warning'
}

// 道路状态颜色
export enum RoadStatusColor {
  NORMAL = '#b4ebaf',
  CLOSE = '#848485',
  ALL = '#c4003e',
  LEFT = '#ffac4d',
  RIGHT = '#ffac4d'
}

export enum PointStatusColor {
  NORMAL = '#f5f5f5',
  CLOSE = '#848485',
  ALL = '#c4003e'
}

// 站点类型翻译
export enum SiteType {
  STATION = '班车站点',
  SENTRY = '岗哨'
}

// 站点类型标签
export enum SiteTypeTag {
  STATION = '',
  SENTRY = ''
}

export enum SiteTypeColor {
  STATION = '#f0f18b',
  SENTRY = '#3b61da'
}

// 站点状态翻译
export enum SiteStatus {
  NORMAL = '正常',
  HIDE = '隐藏'
}

// 站点状态标签
export enum SiteStatusTag {
  NORMAL = 'success',
  HIDE = 'info'
}
