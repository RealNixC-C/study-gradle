import { useEffect, useState } from "react"

function StudyEffect() {
  const [count, setCount] = useState(0);

  function increase() {
    setCount(count + 1);
  }

    // 빈배열 일때는 한번만, 값이 있으면 State가 변경될때 랜더링(특정한 State가 변경 될때)
  // 1. 빈배열 최초 마운트시 변경
  useEffect(()=>{
    console.log("EFFECT1");
  }, [])

  // 2. Mount될때만 실행 (첫 랜더링)
  // 의존성 배열
  useEffect(()=>{
    console.log("EFFECT2");
  }, [count])

  useEffect(()=> {

    // Component가 Unmount 될 때 실행하고 싶은 코드
    // clean up 코드
    return ()=> {
      
    }

  })

    return (
      <>
        <h1>Use Effects</h1>
        <h1>{count}</h1>
        <button onClick={increase}>CLICK</button>
      </>
    )

}

export default StudyEffect