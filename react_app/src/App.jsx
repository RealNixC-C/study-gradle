import Header from "./layout/Header"
import AppRoutes from "./route/AppRoutes"
import StudyProps from "./study/StudyProps"

function App() {

  let age = 10;
  let obj = {age:30, user:'realnixc'}

  return (
    <div>
      <Header></Header>
      {/* <StudyProps obj={obj}></StudyProps> */}
      {/* <StudyProps age='20' user='nixc'></StudyProps> */}
      {/* <StudyProps m={obj}></StudyProps> */}
      <AppRoutes></AppRoutes>
    </div>
  )
}

export default App
