import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Menu,
  MenuItem,
  Box,
  IconButton,
  Drawer,
  TextField,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";

export default function Navbar() {
  const [anchorEl, setAnchorEl] = useState(null);
  const [drawerOpen, setDrawerOpen] = useState(false);

  const handleOpenMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setAnchorEl(null);
  };

  const toggleDrawer = (open) => () => {
    setDrawerOpen(open);
  };

  return (
    <>
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
            <Button
              color="inherit"
              startIcon={<SearchIcon />}
              onClick={toggleDrawer(true)}
            >
              Search a Product
            </Button>
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

      <Drawer anchor="top" open={drawerOpen} onClose={toggleDrawer(false)}>
        <Box sx={{ p: 3 }}>
          <TextField
            fullWidth
            placeholder="Search for a product..."
            variant="outlined"
          />
        </Box>
      </Drawer>
    </>
  );
}
