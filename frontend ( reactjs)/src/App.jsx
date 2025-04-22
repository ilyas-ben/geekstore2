import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  BrowserRouter,
} from "react-router-dom";
import Header from "./Header";

import Home from "./Home";
import AboutUsPage from "./AboutUsPage";
import Login from "./Login";
import AddProductForm from "./products/AddProductForm";
import ProductsByCategory from "./products/ProductsByCategory";
import CategoryList from "./products/CategoriesList";
import ProductsList from "./products/ProductsList";
import AuthProvider from "./components/security/AuthContext";
import HeaderComponent from "./components/Header/HeaderComponent";
import FooterComponent from "./components/Footer/FooterComponent";

function App() {
  const logout = async () => {
    await fetch("http://localhost:8080/logout");
  };

  return (
    <AuthProvider>
      <BrowserRouter>
        <HeaderComponent />
        <Header />
        <Routes>
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
