import { useState, useEffect } from "react";

function List() {
  const [boards, setBoards] = useState([]);
  const [page, setPages] = useState(0);
  
  function next() {
        setPages(page + 1);
      }

  // 처음 한번과 page가 바꼈을때만 랜더링 실행
  useEffect(() => {
    fetch(`http://localhost/notice?page=${page}`)
      .then(r => r.json())
      .then(r => {
        console.log(r)
  
        const b = r.content.map(v =>
        <li key={v.boardNo}>{v.boardTitle}</li>
        
        )
        setBoards(b);
      });
  
  }, [page])


  return(
    <>
    <h1>List Page</h1>
    <ul>
      {boards}
    </ul>
    <div>
      <h3>Page : {page}</h3>
      <button onClick={next}>NEXT</button>
    </div>
    </>
  )
}

export default List