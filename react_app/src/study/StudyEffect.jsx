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

  // 3. UnMount(DOM에서 사라질때) return 으로 clean up 코드 작성
  useEffect(()=> {
    return ()=> {
      // 안에 내용 작성가능
    }
  })

  // 4. 빈배열조차 선언하지 않았을때
  // 모든 렌더링 이후마다 실행됨
  useEffect(()=> {
    console.log("값이 변경될때 마다 실행")
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