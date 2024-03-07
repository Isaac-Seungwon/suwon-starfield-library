/* script.js */
/* 도서 관리 폼 */
const bookForm = $('#bookForm');

/* 도서 테이블의 목록을 보여주는 테이블 */
const bookTable = $('#bookTable').find('tbody');

/* 데이터를 렌더링하는 함수 */
const renderBooks = (books) => {
    // 테이블 내용 초기화
    bookTable.empty();

    // 각 도서를 반복하며 테이블에 추가
    books.forEach(book => {
        const row = `<tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author.name}</td>
                        <td>${formatPrice(book.price)}</td>
                        <td>
                            <button onclick="editBook(${book.id})">편집</button>
                            <button onclick="deleteBook(${book.id})">삭제</button>
                        </td>
                    </tr>`;
        // 행을 테이블에 추가
        bookTable.append(row);
    });
};

/* 폼 제출 이벤트 리스너 */
bookForm.submit((event) => {
    event.preventDefault(); // 폼의 기본 동작 방지

    // 책 데이터 수집
    const bookData = {
        id: $('#bookId').val(),
        title: $('#title').val(),
        author: { id: null, name: $('#author').val() }, // 작가 정보
        price: $('#price').val()
    };

    // 책 저장
    saveBook(bookData);

    // HTML 요소 초기화
    $('#bookId, #title, #author, #price').val('');
    bookForm.trigger('reset'); // 폼 초기화
});

/* 책 저장하는 함수, 책 수정하는 함수 */
const saveBook = (book) => {
    let url = '/starfield/books';
    let method = 'POST';

    // 기존의 책을 수정하는 경우
    if (book.id) {
        url += `/${book.id}`;
        method = 'PUT';
    }

    // AJAX를 통해 책 저장 요청 전송
    $.ajax({
        type: method,
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(book),
        success: () => {
            // 책 저장 성공 시 목록 다시 불러오기
            getAllBooks();
        },
        error: (xhr, status, error) => {
            console.error('Error:', error);
        }
    });
};

/* 책 수정할 때 해당 책 데이터 불러오는 함수 */
const editBook = (id) => {
    // AJAX를 통해 책 정보 요청
    $.ajax({
        type: 'GET',
        url: `/starfield/books/${id}`,
        success: (data) => {
            // 폼에 책 데이터 채우기
            $('#bookId, #title, #author, #price').val('');
            $('#bookId').val(data.id);
            $('#title').val(data.title);
            $('#author').val(data.author.name);
            $('#price').val(data.price);
        },
        error: (xhr, status, error) => {
            console.error('Error:', error);
        }
    });
};

/* 책 삭제하는 함수 */
const deleteBook = (id) => {
    // 사용자에게 확인을 받기 위한 경고창 띄우기
    if (confirm("정말로 삭제하시겠습니까?")) {
        // 사용자가 확인을 클릭한 경우 책 삭제 요청 보내기
        fetch(`/starfield/books/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.status === 204) {
                // 삭제 성공 시 목록 다시 불러오기
                getAllBooks();
            }
        })
        .catch(error => console.error('Error:', error));
    }
};

/* 페이지 로드 시 책 목록 가져오기 */
const getAllBooks = () => {
    // 책 목록 요청
    fetch('/starfield/books/all')
    .then(response => response.json())
    .then(renderBooks)
    .catch(error => console.error('Error:', error));
};

/* 가격에 쉼표 추가 */
const formatPrice = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

/* 페이지 로드 시 책 목록 초기화 */
$(document).ready(getAllBooks);
