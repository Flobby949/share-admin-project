import http from '@/api'
import { _API } from '@/api/axios/servicePort'
import { SysTag } from '@/api/interface'
export type EChartsDataType = {
  name: string
  value: number
}

export type IndexDataReturnType = {
  allUserCount: number
  todayUserCheckCount: number
  resourceCount: number
  resourceUnAuditCount: number
  resourceCountMap: EChartsDataType
  tagList: SysTag.ResTagList[]
}
export const indexDashboard = () => {
  return http.get<IndexDataReturnType>(_API + '/index/dashboard')
}
