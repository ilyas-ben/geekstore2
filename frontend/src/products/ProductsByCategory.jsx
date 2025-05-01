import { useParams } from "react-router-dom";
import ProductsList from "./ProductsList";
import { useEffect, useState } from "react";

const ProductsByCategory = () => {
    let { categoryId } = useParams();

    const [products, setProducts] = useState([]);
    const [categoryName, setCategoryName ] = useState("undefined category name")

    useEffect(() => {
        fetchProducts(categoryId);
    }, []);



   async function fetchProducts(categoryId) {
        await fetch('http://localhost:8080/products/bycategory/' + categoryId)
            .then(response => {
                if (!response.ok) {
                   // Swal.fire("Error ! status = " + response.status);
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Products:', data);
                setCategoryName(data[0].category.name);
                setProducts(data);
                // Further processing of the fetched data
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });



    };

    return (
        <div className="container">
            <div className="text-center"><h2>{categoryName}</h2></div>
            <div className="row">
                {products.map(product => (
                    <div className="col-md-4 mb-4" key={product.id}>
                        <div className="card">
                            {/* <img src={product.imageUrl} className="card-img-top" alt={product.name}/> */}
                            <div className="card-body">
                                <h5 className="card-title">{product.name}</h5>
                                <p className="card-text">{product.description}</p>
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


export default ProductsByCategory;