import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Header';
import ProductsList from './ProductsList';
import Home from './Home';
import AddProductForm from './AddProductForm';
import ProductsByCategory from './ProductsByCategory';
import CategoryList from './CategoriesList';
import AboutUsPage from './AboutUsPage';
import Login from './Login';


function App() {
    return (
        <Router>
            <>
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/products" element={<ProductsList />} />
                    <Route path='/products/add' element={<AddProductForm />} />
                    <Route path='/products/bycategory/:categoryId' element={<ProductsByCategory />} />
                    <Route path='/categories' element={<CategoryList />} />

                    <Route path="/aboutme" element={<AboutUsPage />} />

                    <Route path='/login' element={<Login />} />

                    {/* <Route path='/categories' element={<CategoryList/>}/> */}
                </Routes>
            </>
        </Router>
    );
}

export default App;
