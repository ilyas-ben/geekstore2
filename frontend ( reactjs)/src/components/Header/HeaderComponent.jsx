import { useContext } from "react";
import { AuthContext } from "../security/AuthContext";

export default function HeaderComponent() {
  const { number, setNumber } = useContext(AuthContext);

  return (
    <div style={{ display: "block" }}>
      This is new Header Componnent : <p>Current number: {number}</p>
      <button onClick={() => setNumber(number + 1)}>Increment</button>
      <button onClick={() => setNumber(0)}>reset</button>
    </div>
  );
}
