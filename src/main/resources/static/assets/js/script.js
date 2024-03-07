/* script.js */
$(document).ready(() => {
    const bookForm = $('#bookForm'); // 도서 관리 폼
    const bookTable = $('#bookTable').find('tbody'); // 도서 목록 테이블의 tbody 요소

    // 도서 목록을 렌더링하는 함수
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
                                <button class="edit-btn" data-id="${book.id}">편집</button>
                                <button class="delete-btn" data-id="${book.id}">삭제</button>
                            </td>
                        </tr>`;
            bookTable.append(row);
        });

        // 각 행의 편집 및 삭제 버튼에 이벤트 리스너 추가
        $('.edit-btn').click((event) => {
            const id = $(event.currentTarget).data('id');
            editBook(id);
        });

        $('.delete-btn').click((event) => {
            const id = $(event.currentTarget).data('id');
            deleteBook(id);
        });
    };

    // 폼 제출 이벤트 리스너
    bookForm.submit((event) => {
        event.preventDefault(); // 폼의 기본 동작 방지

        // 책 데이터 수집
        const bookData = {
            id: $('#bookId').val(),
            title: $('#title').val(),
            author: { id: null, name: $('#author').val() }, // 작가 정보
            price: $('#price').val()
        };

        saveBook(bookData); // 책 저장
        $('#bookId, #title, #author, #price').val(''); // HTML 요소 초기화
        bookForm.trigger('reset'); // 폼 초기화
    });

    // 책 저장하는 함수, 책 수정하는 함수
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
                getAllBooks(); // 책 저장 성공 시 목록 다시 불러오기
            },
            error: (xhr, status, error) => {
                console.error('Error saving book:', error);
                alert('책 저장 중 오류가 발생했습니다.'); // 오류 메시지 표시
            }
        });
    };

    // 책 수정할 때 해당 책 데이터 불러오는 함수
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
                console.error('Error fetching book data:', error);
                alert('책 정보를 가져오는 중 오류가 발생했습니다.'); // 오류 메시지 표시
            }
        });
    };

    // 책 삭제하는 함수
    const deleteBook = (id) => {
        if (confirm("정말로 삭제하시겠습니까?")) {
            fetch(`/starfield/books/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.status === 204) {
                    getAllBooks(); // 삭제 성공 시 목록 다시 불러오기
                }
            })
            .catch(error => {
                console.error('Error deleting book:', error);
                alert('책 삭제 중 오류가 발생했습니다.'); // 오류 메시지 표시
            });
        }
    };

    // 페이지 로드 시 책 목록 가져오기
    const getAllBooks = () => {
        fetch('/starfield/books/all')
        .then(response => response.json())
        .then(renderBooks) // 책 목록 렌더링
        .catch(error => {
            console.error('Error fetching book list:', error);
            alert('책 목록을 가져오는 중 오류가 발생했습니다.'); // 오류 메시지 표시
        });
    };

    // 가격에 쉼표 추가하는 함수
    const formatPrice = (price) => {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    };

    getAllBooks(); // 페이지 로드 시 책 목록 초기화
});
