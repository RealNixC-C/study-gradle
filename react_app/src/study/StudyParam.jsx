import { useLocation, useParams, useSearchParams } from "react-router-dom"

function StudyParam (){

    // 파라미터 꺼내오는 방법

    // url? => 쿼리스트링
    // url/params/no => 파라미터(RESTful)

    // 방법 1. useSearchParams();
    // 여러개일때 사용
    // 쿼리스트링 O, RESTful X
    // const [param, setParam] = useSearchParams();
    // console.log(param.get("no"));
    // console.log(param.get("name"));

    // 방법 2. useParam(); RESTful 방식
    // 쿼리스트링 X, RESTful O
    // const { no, name } = useParams();
    // console.log(no);
    // console.log(name);

    // 방법 3. useLocation
    // 쿼리스트링 O, RESTful X
    // const loc = useLocation();
    // console.log(loc);
    // console.log(loc.search);

    // const us = new URLSearchParams(loc.search);
    // console.log(us.get("no"));
    // console.log(us.get("name"));
    
    // 방법 4. state 
    // 쿼리스트링 O, RESTful X
    // URL에 파라미터 노출안됨
    const loc = useLocation();
    if(loc.state != null) {
        console.log(loc.state.age);
        console.log(loc.state.user);
    }

    // 방법 5. 바로 꺼내기
    // console.log(useParams().no);
    // console.log(useParams().name);

    return (
        <>
            <h1>Study Param</h1>
        </>
    )

}

export default StudyParam