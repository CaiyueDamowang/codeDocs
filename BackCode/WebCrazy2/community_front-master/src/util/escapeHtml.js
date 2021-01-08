import faces from '@/assets/js/face'

const escapeHtml = (val = '') => {
  if (!val) return ''
  // 替换表情
  let result = val
  console.log('result :', result)
  let face = /\sface\[\W{1,}]/g
  if (face.test(result)) {
    let group = result.match(face)
    console.log(group)
    group.map((item) => {
      const key = item.match(/\[\S+\]/g)[0]
      result = result.replace(item, `<img src="${faces[key]}">`)
    })
  }
  // 图片替换
  const img = /img\[\S+\]/g
  if (img.test(result)) {
    const group = result.match(img)
    group.map((item) => {
      result = result.replace(item, `<img src="${item.substr(4,
        item.length - 5)}">`)
    })
  }
  // 链接替换 a(链接)[标题]
  const link = /\sa\(\S+\]/g
  if (link.test(result)) {
    const group = result.match(link)
    const title = /\((.+)\)/
    const linkName = /\[(.+)\]/
    group.map((item) => {
      const nameGroup = item.match(linkName)
      let name = ''
      if (nameGroup.length > 0) {
        name = nameGroup[1]
      }
      const linkGroup = item.match(title)
      let link = ''
      if (linkGroup.length > 0) {
        link = linkGroup[1]
      }
      result = result.replace(item, `<a href="${link}">${name}</a>`)
    })
  }
  // 引用替换
  result = result.replace(/\[quote\]/g, '<div class="layui-elem-quote">')
  result = result.replace(/\[\/quote\]/g, '</div>')
  // 代码块替换
  // hr替换
  return result
}
export {
  escapeHtml
}
