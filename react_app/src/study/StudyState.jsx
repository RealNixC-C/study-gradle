import { useState } from "react";

function StudyState() {

  const [count, setCount] = useState(1); // 상태값이 바뀌면 재랜더링

  function increase() {
    
    setCount(count + 1);
  }

  return (
  <>
    <h1>Study State</h1>
    <h1>{count}</h1>
    <button onClick={increase}>CLICK</button>
  </>)
}

export default StudyState;