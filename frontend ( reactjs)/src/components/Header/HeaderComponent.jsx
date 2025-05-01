import React from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Menu,
  MenuItem,
  Box,
  IconButton,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

export default function Navbar() {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleOpenMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="sticky" sx={{ backgroundColor: "#0A1929" }}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1, fontWeight: "bold" }}>
          GeekStore
        </Typography>
        <Box sx={{ display: { xs: "none", md: "flex" }, gap: 2 }}>
          <Button color="inherit">Home</Button>
          <Button color="inherit" onClick={handleOpenMenu}>
            Categories
          </Button>
          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleCloseMenu}
          >
            <MenuItem onClick={handleCloseMenu}>Laptops</MenuItem>
            <MenuItem onClick={handleCloseMenu}>Accessories</MenuItem>
            <MenuItem onClick={handleCloseMenu}>Gaming</MenuItem>
          </Menu>
          <Button color="inherit">Contact</Button>
        </Box>
        <Box sx={{ display: { xs: "flex", md: "none" } }}>
          <IconButton color="inherit" onClick={handleOpenMenu}>
            <MenuIcon />
          </IconButton>
          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleCloseMenu}
          >
            <MenuItem onClick={handleCloseMenu}>Home</MenuItem>
            <MenuItem onClick={handleCloseMenu}>Categories</MenuItem>
            <MenuItem onClick={handleCloseMenu}>Contact</MenuItem>
          </Menu>
        </Box>
      </Toolbar>
    </AppBar>
  );
}
