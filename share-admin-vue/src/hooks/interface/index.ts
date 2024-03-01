export namespace Table {
  export interface Pageable {
    page: number
    limit: number
    total: number
  }
  export interface TableStateProps {
    tableData: any[]
    pageable: Pageable
    searchParam: {
      [key: string]: any
    }
    searchInitParam: {
      [key: string]: any
    }
    totalParam: {
      [key: string]: any
    }
    icon?: {
      [key: string]: any
    }
  }
}

export namespace HandleData {
  export type MessageType = '' | 'success' | 'warning' | 'info' | 'error'
}

export namespace Theme {
  export type GreyOrWeakType = 'grey' | 'weak'
}
