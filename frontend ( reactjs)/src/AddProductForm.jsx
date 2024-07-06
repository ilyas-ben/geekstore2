import React, { useState, useEffect } from 'react';

const AddProductForm = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [quantityStock, setQuantityStock] = useState('');
  const [category, setCategory] = useState('');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = () => {
    fetch('http://localhost:8080/categories',{
      method: 'get',
      headers:{
        'Content-Type': 'application/json',
      }
    })
      .then(response => {
        if (!response.ok) {
          alert("Status code: " + response.status);

        }
        return response.json();
      })
      .then(data => {
        // Process the fetched data here if needed
        setCategories(data);
        console.log('Categories:', data);
      })
      .catch(error => {
        console.error('Error fetching categories:', error);
      });
  };
  

  const handleSubmit = async (e) => {
    e.preventDefault();

    const productData = {
      name,
      description,
      price: parseInt(price),
      quantityStock: parseInt(quantityStock),
      category: { id: parseInt(category) }
    };

    try {
      const response = await fetch('http://localhost:8080/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
      });
      if (!response.ok) {
        Swal.fire("Sorry, couldn't fetch"+response.status+"catg id"+productData.category.id);
        throw new Error('Failed to add product');
      }
      // Reset form fields after successful submission
      
      Swal.fire({
        title: "Product added! Do you wanna add more ?",
        showDenyButton: true,
        confirmButtonText: "Yes",
        denyButtonText: `No`
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          location.reload();
        } else if (result.isDenied) {
          location.href = "/products";
        }
      });
    } catch (error) {
      console.error('Error adding product:', error);
      alert('Failed to add product');
    }
  };

  return (
    <div className="container">
      <h2>Add Product</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            className="form-control"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Description:</label>
          <input
            type="text"
            className="form-control"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Price:</label>
          <input
            type="number"
            className="form-control"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Quantity in Stock:</label>
          <input
            type="number"
            className="form-control"
            value={quantityStock}
            onChange={(e) => setQuantityStock(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Category:</label>
          <select
            className="form-control"

            onChange={(e) => setCategory(e.target.value)}
            required
          >
            
            {categories.map(category => (
              <option key={category.id} value={category.id}>{category.name}</option>
            ))}
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Add Product</button>
      </form>
    </div>
  );
};

export default AddProductForm;
