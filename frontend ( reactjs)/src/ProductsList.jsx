import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';



function ProductsList(props) {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        document.title = 'Products';
        fetchProducts();
    }, []);

    function fetchProducts() {
        fetch('http://localhost:8080/products')
            .then(response => {
                if (!response.ok) {
                    Swal.fire("Error ! status = " + response.status);
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Products:', data);
                setProducts(data);
                // Further processing of the fetched data
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });


    };



    return (
        <div className="container">
            <div className="text-center"><h2>Our products</h2></div>
            <div className="row">
                {products.map(product => (
                    <div className="col-md-4 mb-4" key={product.id}>
                        <div className="card">
                            {/* <img src={product.imageUrl} className="card-img-top" alt={product.name}/> */}
                            <div className="card-body">
                                <h5 className="card-title">{product.name}</h5>
                                
                                <p className="card-text"><strong>Price: ${product.price}</strong></p>
                                {/* Additional fields as needed */}
                                <a href="#" className="btn btn-secondary">View Details</a>
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
