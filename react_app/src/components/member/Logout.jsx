import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Logout () {

  sessionStorage.removeItem("accessToken");
  localStorage.removeItem("accessTOken");

  const nav = useNavigate();

  useEffect(()=>{
    nav("/")
  })
  

}