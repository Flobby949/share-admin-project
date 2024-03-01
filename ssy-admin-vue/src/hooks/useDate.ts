export const useDate = () => {
  // 比较日期
  const compare = (endtime: string) => {
    const date1 = new Date()
    const date2 = new Date(endtime)
    // console.log('今天', date1)
    // console.log('到期', date2)
    if (date1 > date2) {
      return false
    } else if (date1 < date2) {
      return true
    } else {
      return true
    }
  }

  return {
    compare
  }
}
