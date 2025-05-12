import { Button, Grid, Paper, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { request } from "../../helpers/axios_helper";
import { AddShoppingCart } from "@mui/icons-material";

const ProductByID = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await request("get", `/products/${id}`);
        setProduct(response.data);
      } catch (err) {
        setError(err?.message || "Error loading product");
      } finally {
        setLoading(false);
      }
    };
    if (id) fetchProduct();
  }, [id]);

  return (
    <Paper elevation={3} sx={{ padding: 3, mt: 4 }}>
      <Grid container spacing={2}>
        <Grid item xs={12} md={5}>
          <img
            src={product?.image || `/${product?.imagePath}`}
            alt={product?.name || "Product"}
            style={{ width: "100%", height: "auto" }}
          />
        </Grid>

        <Grid item xs={12} md={7}>
          <Typography variant="h4" gutterBottom>
            {product?.name || "Product"}
          </Typography>
          <Typography variant="h6" color="text.secondary">
            {product?.price ? `${product.price} €` : "Price not available"}
          </Typography>
          <Typography variant="body1" sx={{ mt: 2 }}>
            {product?.description || "Description not available"}
          </Typography>
          <Button
            startIcon={<AddShoppingCart />}
            variant="contained"
            sx={{ mt: 3 }}
          >
            Add to cart
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default ProductByID;
