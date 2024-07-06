import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

function CategoryList() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/categories')
            .then(response => {
                if (!response.ok) {
                    throw new Error('There is an error by the server.');
                }
                return response.json();
            })
            .then(data => {
                setCategories(data);
            })
            .catch(error => {
                console.error('Error fetching categories:', error);
            });
    }, []);

    return (
        <div className="container">
            <br />
            <div className="text-center"><h2>Categories of products</h2></div>
            <div className="row">
        {categories.map((category, index) => (
            <div className="col-md-4 mb-4" key={index}>
                <div className="card" style={{ width: '18rem' }}>
                    <img src="..." className="card-img-top" alt="..." />
                    <div className="card-body">
                        <h5 className="card-title">{category.name}</h5>
                        <a href={"/products/bycategory/" + category.id} className="btn btn-primary">Display products</a>
                    </div>
                </div>
            </div>
        ))}
    </div></div>
    );
}

export default CategoryList;
