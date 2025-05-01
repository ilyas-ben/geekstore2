import React from 'react';
import { Box, Container, Grid, Typography, Link, IconButton } from '@mui/material';
import FacebookIcon from '@mui/icons-material/Facebook';
import InstagramIcon from '@mui/icons-material/Instagram';
import TwitterIcon from '@mui/icons-material/Twitter';

export default function FooterComponent() {
  return (
    <Box sx={{ backgroundColor: '#0A1929', color: 'white', py: 5, mt: 10 }}>
      <Container maxWidth="lg">
        <Grid container spacing={4}>
          <Grid item xs={12} md={4}>
            <Typography variant="h6" gutterBottom fontWeight="bold">
              GeekStore
            </Typography>
            <Typography variant="body2">
              Your one-stop shop for all tech enthusiasts.
            </Typography>
          </Grid>
          <Grid item xs={12} md={4}>
            <Typography variant="h6" gutterBottom fontWeight="bold">
              Quick Links
            </Typography>
            <Link href="#" color="inherit" underline="hover" display="block">Home</Link>
            <Link href="#" color="inherit" underline="hover" display="block">Catalogue</Link>
            <Link href="#" color="inherit" underline="hover" display="block">Contact</Link>
          </Grid>
          <Grid item xs={12} md={4}>
            <Typography variant="h6" gutterBottom fontWeight="bold">
              Follow Us
            </Typography>
            <Box sx={{ mt: 1 }}>
              <IconButton color="inherit">
                <FacebookIcon />
              </IconButton>
              <IconButton color="inherit">
                <InstagramIcon />
              </IconButton>
              <IconButton color="inherit">
                <TwitterIcon />
              </IconButton>
            </Box>
          </Grid>
        </Grid>
        <Box textAlign="center" pt={5} fontSize="small">
          © {new Date().getFullYear()} GeekStore. All rights reserved.
        </Box>
      </Container>
    </Box>
  );
}
