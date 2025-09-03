import { useRef } from "react";
import { useState } from "react"

// Ref = 값을 담을 수 있지만 랜더링이 일어나지 않음

function StudyRef() {

  const [count, setCount] = useState(0);
  
  const age = useRef(0)

  function increase() {
    setCount(count + 1)
  }

  function next() {
    age.current = 3;
    console.log(age.current);
  }

  return (
    <>
    <h1>REF</h1>
    <h1>{count}</h1>
    <h1>{age.current}</h1>
    <button onClick={increase}>CLICK</button>
    <button onClick={next}>REF</button>
    </>
  )

}

export default StudyRef