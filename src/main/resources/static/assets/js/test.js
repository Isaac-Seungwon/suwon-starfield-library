/* test.js */
/* 유닛 테스트 - saveBook 함수 */
describe('saveBook 함수', () => {
    it('책을 저장하고 저장 성공 시 모든 책을 다시 불러와야 함', () => {
        // 가짜 책 데이터 생성
        const fakeBook = {
            id: 1,
            title: '테스트 책',
            author: { id: 1, name: '테스트 작가' },
            price: 10000
        };

        // AJAX 요청을 가로채고 가짜 응답 반환
        spyOn($, 'ajax').and.callFake(function(options) {
            options.success(); // 성공 콜백 호출
        });

        // 함수 호출
        saveBook(fakeBook);

        // AJAX 요청이 올바르게 호출되었는지 확인
        expect($.ajax).toHaveBeenCalled();

        // 모든 책을 다시 불러오는 함수가 호출되었는지 확인
        expect(getAllBooks).toHaveBeenCalled();
    });

    it('책 저장 시 실패하면 에러를 콘솔에 출력해야 함', () => {
        // 가짜 책 데이터 생성
        const fakeBook = {
            id: 1,
            title: '테스트 책',
            author: { id: 1, name: '테스트 작가' },
            price: 10000
        };

        // AJAX 요청을 가로채고 가짜 응답 반환
        spyOn($, 'ajax').and.callFake(function(options) {
            options.error(); // 에러 콜백 호출
        });

        // 콘솔 로그를 가로채기 위한 스파이 설정
        spyOn(console, 'error');

        // 함수 호출
        saveBook(fakeBook);

        // 에러가 콘솔에 출력되었는지 확인
        expect(console.error).toHaveBeenCalled();
    });
});

/* 통합 테스트 - getAllBooks 함수 */
describe('getAllBooks 함수', () => {
    it('모든 책을 가져와서 테이블에 렌더링해야 함', () => {
        // 가짜 책 데이터 생성
        const fakeBooks = [
            { id: 1, title: '테스트 책 1', author: { id: 1, name: '테스트 작가 1' }, price: 10000 },
            { id: 2, title: '테스트 책 2', author: { id: 2, name: '테스트 작가 2' }, price: 20000 },
            { id: 3, title: '테스트 책 3', author: { id: 3, name: '테스트 작가 3' }, price: 30000 }
        ];

        // AJAX 요청을 가로채고 가짜 응답 반환
        spyOn(window, 'fetch').and.returnValue(Promise.resolve({
            json: () => Promise.resolve(fakeBooks)
        }));

        // 함수 호출
        getAllBooks();

        // 테이블에 올바르게 렌더링되었는지 확인
        expect(bookTable.children().length).toEqual(fakeBooks.length);
    });
});
