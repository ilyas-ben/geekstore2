import { useEffect, useState } from "react";
import { request } from "../../helpers/axios_helper";

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
    <div className="container py-4">
      <div className="text-center mb-5">
        <h2 className="fw-bold">Product Categories</h2>
      </div>
      <div className="row g-4">
        {categories.map((category, index) => (
          <div className="col-md-4" key={index}>
            <div className="card h-100 shadow-sm">
              <img
                src="https://via.placeholder.com/300x200"
                className="card-img-top"
                alt={category.name}
              />
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{category.name}</h5>
                <a
                  href={`/products/bycategory/${category.id}`}
                  className="btn btn-primary mt-auto"
                >
                  Explore Products
                </a>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CategoryList;
