export class Utils {
  static textAreaScrollDown(id: string) {
    var textarea = document.getElementById(id);
    // console.log(textarea);
    if (textarea) {
      if (textarea.scrollHeight) {
        textarea.scrollTop = textarea.scrollHeight;
      }
    }
  }

  static convertToArrayFromKey(data: any) {
    const array = [];
    for (let key in data) {
      array.push(key);
    }
    return array;
  }
}
