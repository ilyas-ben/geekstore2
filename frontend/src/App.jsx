import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./Header";

import { Container } from "@mui/material";
import AboutUsPage from "./AboutUsPage";
import FooterComponent from "./components/Footer/FooterComponent";
import AuthProvider from "./components/security/AuthContext";
import Home from "./Home";
import Login from "./Login";
import CategoryList from "./components/products/CategoriesList";
import ProductsByCategory from "./components/products/ProductsByCategory";
import ProductsList from "./components/products/ProductsList";
import AddProductForm from "./components/products/AddProductForm";
import ProductByID from "./components/products/ProductByID";

function App() {
  const logout = async () => {
    await fetch("http://localhost:8080/logout");
  };

  return (
    <AuthProvider>
      <BrowserRouter>
        <Header />
        <Container>
          <Routes>
            <Route path="/products/:id" element={<ProductByID />} />
            <Route path="/" element={<Home />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/products/add" element={<AddProductForm />} />
            <Route
              path="/products/bycategory/:categoryId"
              element={<ProductsByCategory />}
            />
            <Route path="/categories" element={<CategoryList />} />
            <Route path="/aboutme" element={<AboutUsPage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/logout"> {logout} </Route>
          </Routes>
        </Container>
        <FooterComponent />
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;

/* 

useEffect(() => {
    console.log('useEffect s\'exécute après chaque rendu.');
});


useEffect(() => {
    console.log('useEffect s\'exécute une seule fois, après le premier rendu.');
}, []);



 */
