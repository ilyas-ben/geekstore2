import { createContext, useMemo, useState } from "react";
import PropTypes from "prop-types";

export const AuthContext = createContext();

export default function AuthProvider({ children }) {
  const [number, setNumber] = useState(0);

  const value = useMemo(() => ({ number, setNumber }), [number, setNumber]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

AuthProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
