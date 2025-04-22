import { useContext } from "react";
import { AuthContext } from "../security/AuthContext";

export default function FooterComponent() {
    // add context
    const { number, setNumber } = useContext(AuthContext);
  return (
    <footer className="bg-light text-center text-lg-start">
        Current number : {number}
      <div className="text-center p-3">
        © 2023 Copyright:
        <a className="text-dark" href="https://mdbootstrap.com/">
          MDBootstrap.com
        </a>
        
      </div>
    </footer>
  );
}
