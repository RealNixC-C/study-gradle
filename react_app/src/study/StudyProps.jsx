// 방법1. 한번에 props로 받기
// 부모에서 보낸 결과들이 객체에 담겨옴
// export default function StudyProps(props) {
// console.log(props.age);

// 방법2. 분해해서 받기
// age="20" user="nixc"
// export default function StudyProps({age, user}) {
//   console.log(age);
//   console.log(user);

// 방법3. 객체보내기
// m = {age:20 user="is"}
// export default function StudyProps(props) {
//   console.log(props.m.age);
//   console.log(props.m.user);
  
// 방법4. 객체명과 동일한 매개변수명 사용
export default function StudyProps({m}) {
  console.log(m.age);
  console.log(m.user);

  return (
    <>
      <h1>Study Props</h1>
    </>
  )

}