import fs from "fs";

const books = JSON.parse(fs.readFileSync("./resources/books.json"));
for (const book of books) {
  delete book.isbn;
  book.id = Math.floor(Math.random() * 1000000000) + Date.now();
  // delete book.count;
  // book.copies = Math.floor(Math.random() * 100);
}

fs.writeFileSync("./resources/books.json", JSON.stringify(books));

// const topics = [
//   "software",
//   "philosophy",
//   "armenia",
//   "sport",
//   "startup",
//   "finance",
//   "business",
//   "science",
//   "technology",
//   "innovation",
// ];

// const books = [];

// for (const topic of topics) {
//   const response = await fetch(
//     `https://www.googleapis.com/books/v1/volumes?q=${topic}&key=${process.env.GOOGLE_BOOKS_API_KEY}&maxResults=40`
//   )
//     .then((i) => i.json())
//     .then((i) => (i = i.items));
//   const updated = response.map((el) => ({
//     id: el.id,
//     title: el.volumeInfo.title,
//     subtitle: el.volumeInfo.subtitle,
//     authors: el.volumeInfo.authors,
//     publisher: el.volumeInfo.publisher,
//     year: parseInt(el.volumeInfo.publishedDate.split("-")[0]),
//     description: el.volumeInfo.description,
//     pages: el.volumeInfo.pageCount,
//     cover: el.volumeInfo.imageLinks?.thumbnail,
//     language: el.volumeInfo.language,
//     link: el.volumeInfo.infoLink,
//     categories: el.volumeInfo.categories,
//     renters: [],
//   }));
//   books.push(...updated);
// }

// fs.writeFileSync("books.json", JSON.stringify(books));
