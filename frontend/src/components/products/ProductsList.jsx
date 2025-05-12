import { AddShoppingCart } from "@mui/icons-material";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Grid,
  Typography,
  Paper,
  TextField,
} from "@mui/material";
import { useEffect, useState } from "react";
import { request } from "../../helpers/axios_helper";
import { addProductToCart } from "../../helpers/cart";
import { useNavigate } from "react-router-dom";

export default function ProductsList({ products: productsProp, categoryName }) {
  const [products, setProducts] = useState(productsProp || []);
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    document.title = "Products";
    if (!productsProp) {
      const fetchProducts = async () => {
        const response = await request("get", "/products");
        setProducts(response.data);
      };
      fetchProducts();
    }
  }, [productsProp]);

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <Box sx={{ p: 4 }}>
      <Typography variant="h4" textAlign="center" mb={4} fontWeight="bold">
        {categoryName ?? "Our Products"}
      </Typography>

      <Grid container spacing={4}>
        {filteredProducts.map((product) => (
          <Grid item xs={12} sm={6} md={4} key={product.id}>
            <Paper>
              <Card
                sx={{
                  height: "100%",
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                }}
              >
                <CardMedia
                  sx={{ height: 140, objectFit: "contain" }}
                  image={product.imagePath}
                  title={product.name}
                  component="img"
                />
                <CardContent>
                  <Typography variant="h6" fontWeight="bold">
                    {product.name}
                  </Typography>
                  <Typography variant="body1" color="text.secondary" mb={2}>
                    Price: ${product.price}
                  </Typography>
                </CardContent>
                <CardActions sx={{ justifyContent: "space-between" }}>
                  <Button
                    size="small"
                    variant="outlined"
                    color="primary"
                    onClick={() => navigate(`/products/${product.id}`)}
                  >
                    View Details
                  </Button>
                  <Button
                    size="small"
                    variant="contained"
                    startIcon={<AddShoppingCart />}
                    onClick={() => addProductToCart(product.id)}
                    color="primary"
                  >
                    Add to Cart
                  </Button>
                </CardActions>
              </Card>
            </Paper>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
