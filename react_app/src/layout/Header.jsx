import { Link } from "react-router-dom"

// a태그 역할을 하는 Link를 모아둔 Header Component
function Header () {

  return (
    <>
      <h1>Header line</h1>
      <Link to="/">Home</Link><br/>
      <Link to="/notice/list">NoticeList</Link><br/>
      <Link to="/study/param?no=10&name=nixc">StudyParam</Link><br/>    
      <Link to="/study/param/10/nixc">StudyParam2</Link><br/>
      <Link to="/study/param" state={{age:10, user:"nixc"}}>StudyParam3</Link><br/>

      <div>
        <Link to="/member/login">Login</Link><br/>
        <Link to="/member/join">Join</Link><br/>
      </div>
      <div>
        <Link to="/member/logout">Logout</Link><br/>
        <Link to="/member/mypage">My Page</Link><br/>
      </div>
    </>
  )

}

export default Header