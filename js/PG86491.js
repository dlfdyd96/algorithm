function solution(sizes) {
  var answer = 0;

  let maxWidth = 0;
  let maxHeight = 0;

  sizes.map(([w, h]) => {
      console.log(w, h);
      return w > h ? [w, h] : [h, w];
  }).forEach(([w, h]) => {
    maxWidth = Math.max(maxWidth, w);
    maxHeight = Math.max(maxHeight, h);
  });

  answer = maxWidth * maxHeight;

  return answer;
}
