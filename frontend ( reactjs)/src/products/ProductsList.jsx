import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import { request } from "../helpers/axios_helper";



function ProductsList(props) {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            document.title = 'Products';
            const response = await request("get", "/products");
            setProducts(response.data);
        };
        fetchProducts();
    }, []);


    return (
        <div className="container">
            <div className="text-center"><h2>Our products</h2></div>
            <div className="row">
                {products.map(product => (
                    <div className="col-md-4 mb-4" key={product.id}>
                        <div className="card">

                            <div className="card-body">
                                <h5 className="card-title">{product.name}</h5>

                                <p className="card-text"><strong>Price: ${product.price}</strong></p>
                                {/* Additional fields as needed */}
                                <a href="#" className="btn btn-secondary" >View Details</a>
                                <a href="#" className="btn btn-primary">Add to Cart</a>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ProductsList;
