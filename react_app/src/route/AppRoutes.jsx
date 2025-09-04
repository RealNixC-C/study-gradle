import { Route, Routes } from "react-router-dom";
import List from "../components/board/List"
import Index from "../components";
import Add from "../components/board/Add";
import StudyParam from "../study/StudyParam";
import Login from "../components/member/Login";

// 요청이 들어왔을때 라우팅 할 주소
// /notice/로 감싼 Route를 생성하여 해당 부분 묶을 수 있음
export default function AppRoutes(){

  return (
    <>
      <Routes>
        <Route path="/" element={<Index/>}></Route>
        <Route path="/notice/">
          <Route path="list" element={<List></List>}></Route>
          <Route path="add" element={<Add></Add>}></Route>
        </Route>
        <Route path="/member/">
          <Route path="login" element={<Login></Login>}></Route>
        </Route>
        <Route path="/study/param" element={<StudyParam></StudyParam>}></Route>
        <Route path="/study/param/:no/:name" element={<StudyParam></StudyParam>}></Route>
      </Routes>
    </>
  )

}