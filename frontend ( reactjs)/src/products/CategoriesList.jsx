import React, { useState, useEffect } from 'react';

import { request } from '../helpers/axios_helper';

function CategoryList() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
          try {
            const response = await request("get", "/categories");
            setCategories(response.data);
          } catch (error) {
            console.error(error);
          }
        };
    
        fetchCategories();
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
