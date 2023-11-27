export interface Ad {
  id: number;
  title: string;
  description: string;
  price: number;
  surface: number;
  accomodation: {
    rooms: number;
    bathrooms: number;
    type: string;
    category: string;
    location: string;
    best: number;
    images: string[];
  }
}

