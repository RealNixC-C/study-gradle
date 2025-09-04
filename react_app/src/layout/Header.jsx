import { Link } from "react-router-dom"

// a태그 역할을 하는 Link를 모아둔 Header Component
function Header () {

  return (
    <>
      <h1>Header line</h1>
      <Link to="/">Home</Link><br/>
      <Link to="/member/login">Member</Link><br/>
      <Link to="/notice/list">Notice</Link><br/>
      <Link to="/study/param?no=10&name=nixc">StudyParam</Link><br/>    
      <Link to="/study/param/10/nixc">StudyParam2</Link><br/>
      <Link to="/study/param" state={{age:10, user:"nixc"}}>StudyParam3</Link><br/>
    </>
  )

}

export default Header