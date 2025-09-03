import { use, useState } from "react";
import { useRef } from "react";

/*
input tag와 연결해서 사용

const n = useRef(""); 
<input type="text" ref={n}/>
*/

function Add () {

  const [name, setName] = useState("test");
  
  const n = useRef("");

  const writer = useRef();
  const title = useRef();
  const content = useRef();

  function get() {
    setName(writer.current.value);

    const params = new URLSearchParams();
    params.append("boardWriter", writer.current.value);
    params.append("boardTitle", title.current.value);
    params.append("boardContent", content.current.value);

    fetch("http://localhost/notice/add", {
      method : "POST",
      body : params
    })
    .then(r => r.json())
    .then(r => {
      if(r==true) {
      console.log("등록완료")
      } else {
        console.log("등록실패")
      }
    })
  }

  function print() {
    console.log(writer.current.value)
    console.log(title.current.value)
    console.log(content.current.value)
  }

  return (
    <>
      <h1>Add Page</h1>
      <h3>Input : {name}</h3>
      작성자
      <input type="text" ref={writer}/><br></br>
      제목
      <input type="text" ref={title}/><br></br>
      내용
      <textarea ref={content}></textarea>
      <br></br>
      <button onClick={get}>INSERT</button>
      <button onClick={print}>PRINT</button>
    </>
  )

}

export default Add