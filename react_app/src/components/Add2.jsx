import { useState } from "react"

const [add, setAdd] = useStateate({
  boardTitle:"",
  boardWriter:"",
  boardContent:""
})

function inputChange(e) {

  setAdd((prevState) => ({
    ...prevState,
    [e.target.name]:e.target.value
  }))
}

function get() {
  console.log(add)
}

/*
  방법 3
  form Tag 전체를 안에 담아 보내는 방법
*/
function add(e) {
  e.preventDefault();
  const formdata = new FormData(e.target); // <form></form>
}