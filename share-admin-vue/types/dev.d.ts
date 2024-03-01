declare interface UserType {
  pkId: number
  account: string
  nickname: string
  avatar?: any
  phone: string
  company?: any
  gender: number
  endTime?: string // 会员到期时间
  createTime?: string
}

declare interface ReqPageUser {
  asc?: boolean
  company?: string
  limit: number
  nickname?: string
  order?: string
  page: number
  phone?: string
}
