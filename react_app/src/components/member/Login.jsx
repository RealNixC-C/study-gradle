export default function Login() {

  // const nav = useNavigate();

  function login(e) {
    e.preventDefault();

    const form = new FormData(e.target);

    let all = Object.fromEntries(form.entries())

    console.log(all);

    fetch("http://localhost/api/member/login", {
      method : "POST",
      body : form
    })
    // .then(r => r.text())
    // .then(r => console.log(r))
    .then(r => {
      const header = r.headers;
      console.log(header.get("accessToken"));
      localStorage.setItem("accessToken", header.get("accessToken"));
      // localStorage.setItem("refreshToken", header.get("refreshToken"));
      // nav("/");
    })
    .catch(e => console.log(e))
  }

  return (
    <>
    <h1>Login Page</h1>
    <form onSubmit={login}>
      <div>
        <input type="text" name="username"/>
        <input type="text" name="password"/>
        <button>Login</button>
      </div>
    </form>
    </>
  )

}