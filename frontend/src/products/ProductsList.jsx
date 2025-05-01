import React, { useEffect, useState } from "react";
import { request } from "../helpers/axios_helper";
import { addProductToCart } from "../helpers/cart";
import { Typography, Grid, Card, CardContent, CardActions, Button, IconButton, Box } from "@mui/material";
import { AddShoppingCart } from "@mui/icons-material";

export default function ProductsList() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    document.title = "Products";
    const fetchProducts = async () => {
      const response = await request("get", "/products");
      setProducts(response.data);
    };
    fetchProducts();
  }, []);

  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" textAlign="center" mb={4} fontWeight="bold">
        Our Products
      </Typography>
      <Grid container spacing={4}>
        {products.map((product) => (
          <Grid item xs={12} sm={6} md={4} key={product.id}>
            <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
              <CardContent>
                <Typography variant="h6" gutterBottom fontWeight="bold">
                  {product.name}
                </Typography>
                <Typography variant="body1" color="text.secondary" mb={2}>
                  Price: ${product.price}
                </Typography>
              </CardContent>
              <CardActions sx={{ justifyContent: 'space-between' }}>
                <Button size="small" variant="outlined" color="secondary">
                  View Details
                </Button>
                <Button
                  size="small"
                  variant="contained"
                  startIcon={<AddShoppingCart />}
                  onClick={() => addProductToCart(product.id)}
                  sx={{ backgroundColor: '#FF6F00' }}
                >
                  Add to Cart
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
