import { useEffect, useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Menu,
  MenuItem,
  Box,
  Badge,
  Drawer,
  List,
  ListItem,
  ListItemText,
  Avatar,
  Divider,
  Fade,
} from "@mui/material";
import {
  ShoppingCart,
  Menu as MenuIcon,
  Logout,
  Login,
  Home,
  Category,
  Info,
} from "@mui/icons-material";
import { getAuthToken, logout } from "./helpers/axios_helper";
import { jwtDecode } from "jwt-decode";
import { getCurrentUsersCart } from "./helpers/cart";

export default function Header() {
  const [token, setToken] = useState(null);
  const [cartItems, setCartItems] = useState([]);
  const [anchorEl, setAnchorEl] = useState(null);
  const [drawerOpen, setDrawerOpen] = useState(false);

  useEffect(() => {
    const tokenFromStorage = getAuthToken();
    if (tokenFromStorage && tokenFromStorage !== "undefined") {
      setToken(jwtDecode(tokenFromStorage));
    } else {
      setToken(null);
    }
  }, []);

  const fetchCart = async () => {
    const cartData = await getCurrentUsersCart();
    if (cartData) {
      setCartItems(cartData.cartItems);
    }
  };

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleMenuClose = () => {
    setAnchorEl(null);
  };
  const toggleDrawer = (open) => () => {
    setDrawerOpen(open);
    fetchCart();
  };

  return (
    <>
      <AppBar
        position="static"
        elevation={4}
        sx={{ backgroundColor: "#1565c0" }}
      >
        <Toolbar>
          <Typography
            variant="h5"
            component="a"
            href="/"
            sx={{
              textDecoration: "none",
              color: "inherit",
              fontWeight: 600,
              flexGrow: 1,
            }}
          >
            ILOUSE.MA
          </Typography>

          <Box
            sx={{
              display: { xs: "none", md: "flex" },
              gap: 3,
              alignItems: "center",
            }}
          >
            <Button
              color="inherit"
              href="/"
              startIcon={<Home />}
              sx={{ fontWeight: 500 }}
            >
              Home
            </Button>
            <Button
              color="inherit"
              href="/categories"
              startIcon={<Category />}
              sx={{ fontWeight: 500 }}
            >
              Categories
            </Button>
            <Button
              color="inherit"
              href="/aboutme"
              startIcon={<Info />}
              sx={{ fontWeight: 500 }}
            >
              About Me
            </Button>
            <IconButton color="inherit" onClick={toggleDrawer(true)}>
              <Badge badgeContent={cartItems.length} color="error">
                <ShoppingCart />
              </Badge>
            </IconButton>
            {token ? (
              <>
                <IconButton
                  color="inherit"
                  onClick={handleMenuOpen}
                  sx={{ ml: 1 }}
                >
                  <Avatar sx={{ bgcolor: "#0d47a1" }}>
                    {token.sub.charAt(0).toUpperCase()}
                  </Avatar>
                </IconButton>
                <Menu
                  anchorEl={anchorEl}
                  open={Boolean(anchorEl)}
                  onClose={handleMenuClose}
                  TransitionComponent={Fade}
                >
                  <MenuItem disabled>{token.sub}</MenuItem>
                  <Divider />
                  <MenuItem
                    onClick={() => {
                      logout();
                      handleMenuClose();
                    }}
                  >
                    <Logout fontSize="small" sx={{ mr: 1 }} /> Logout
                  </MenuItem>
                </Menu>
              </>
            ) : (
              <Button
                color="inherit"
                href="/login"
                startIcon={<Login />}
                sx={{ fontWeight: 500 }}
              >
                Sign In
              </Button>
            )}
          </Box>

          <Box sx={{ display: { xs: "flex", md: "none" } }}>
            <IconButton color="inherit" onClick={handleMenuOpen}>
              <MenuIcon />
            </IconButton>
            <Menu
              anchorEl={anchorEl}
              open={Boolean(anchorEl)}
              onClose={handleMenuClose}
              TransitionComponent={Fade}
            >
              <MenuItem component="a" href="/">
                Home
              </MenuItem>
              <MenuItem component="a" href="/categories">
                Categories
              </MenuItem>
              <MenuItem component="a" href="/aboutme">
                About Me
              </MenuItem>
              <MenuItem onClick={toggleDrawer(true)}>
                Cart ({cartItems.length})
              </MenuItem>
              {token ? (
                <>
                  <Divider />
                  <MenuItem disabled>{token.sub}</MenuItem>
                  <MenuItem
                    onClick={() => {
                      logout();
                      handleMenuClose();
                    }}
                  >
                    Logout
                  </MenuItem>
                </>
              ) : (
                <MenuItem component="a" href="/login">
                  Sign In
                </MenuItem>
              )}
            </Menu>
          </Box>
        </Toolbar>
      </AppBar>

      <Drawer anchor="right" open={drawerOpen} onClose={toggleDrawer(false)}>
        <Box sx={{ width: 320, p: 2 }}>
          <Typography variant="h6" sx={{ mb: 2 }}>
            Shopping Cart
          </Typography>
          <Divider />
          <List>
            {cartItems.length > 0 ? (
              cartItems.map((item, index) => (
                <ListItem key={index} divider>
                  <ListItemText
                    primary={`${item.quantity} × ${item.product.name}`}
                    secondary={`USD ${item.product.price * item.quantity}`}
                  />
                </ListItem>
              ))
            ) : (
              <ListItem>
                <ListItemText primary="Your cart is empty." />
              </ListItem>
            )}
          </List>
          <Divider sx={{ my: 2 }} />
          <Button variant="contained" color="primary" fullWidth>
            Checkout
          </Button>
        </Box>
      </Drawer>
    </>
  );
}
