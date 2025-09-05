import { Component, use, useState } from "react";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import File from "./File";

/*
input tag와 연결해서 사용

const n = useRef(""); 
<input type="text" ref={n}/>
*/

function Add () {

  const [name, setName] = useState("test");

  const [files, setFiles] = useState([]);
  const fileIdx = useRef(0);

  const writer = useRef();
  const title = useRef();
  const content = useRef();
  const navigate = useNavigate();

  function get() {
    setName(writer.current.value);

    const params = new URLSearchParams();
    params.append("boardWriter", writer.current.value);
    params.append("boardTitle", title.current.value);
    params.append("boardContent", content.current.value);

    fetch("http://localhost/api/notice/add", {
      method : "POST",
      body : params
    })
    .then(r => r.json())
    .then(r => {
      if(r==true) {
        console.log("등록완료");
        navigate('/notice/list');
      } else {
        console.log("등록실패");
      }
    })
  }

  function send(e){
        e.preventDefault();
        const formdata = new FormData(e.target) //<form></form>
        fetch("http://localhost/api/notice/add", {
            method:"POST",
            body:formdata,
            headers : {
              
            }
        })
        .then(r=>r.json())
        .then(r=>{
            if(r==true){
              console.log("등록")
              // navigate("/notice/notice/list");
            } else {
              alert("등록실패")
            }
        })
    }

  function print() {
    console.log(writer.current.value)
    console.log(title.current.value)
    console.log(content.current.value)
  }

  function addFile() {
    if (files.length > 4) {
      alert("최대 5개 까지 가능");
      return;
    }

    const newFile = { id: fileIdx.current };
    fileIdx.current++;

    setFiles([...files, newFile]);
  }

  function deleteFile(id) {
    console.log(id);
    setFiles(files.filter((f) => f.id !== id));
  }

  return (
    <>
      <h1>Add Page</h1>
      <h3>Input : {name}</h3>
      <form onSubmit={send}>
        작성자
        <input type="text" name="boardTitle" ref={writer}/><br></br>
        제목
        <input type="text" name="boardWriter" ref={title}/><br></br>
        내용
        <textarea name="boardContent" ref={content}></textarea>

        <div>
          {files.map((f) => (
            <div key={f.id}>
              <input type="file" name="attaches"/>
              <button type="button" onClick={() => deleteFile(f.id)}>X</button>
            </div>
          ))}
        </div>
        <div>
          <button type="button" onClick={addFile}>ADD FILE</button>
        </div>

        <br></br>
        <button onClick={get}>INSERT</button>
        <button onClick={print}>PRINT</button>
        <button>USEFORM</button>
      </form>
    </>
  )

}

export default Add