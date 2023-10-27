export interface Ad {
  id: number;
  title: string;
  description: string;
  price: number;
  timestamp: string; // You can use a string or Date for timestamps
  accommodationId: number;
  userId: number;
}
