from copy import deepcopy

EMPTY = 0
BLACK = 1
WHITE = 2


class Board:
    def __init__(self):
        self.board = [[0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0],
                      [0, 0, 0, 0, 0, 0, 0, 0]]
        self.board[3][4] = BLACK
        self.board[4][3] = BLACK
        self.board[3][3] = WHITE
        self.board[4][4] = WHITE
        self.valid_moves = []

    def __getitem__(self, i, j):
        return self.board[i][j]

    def lookup(self, row, column, color):
        if color == BLACK:
            other = WHITE
        else:
            other = BLACK

        places = []

        if (row < 0 or row > 7 or column < 0 or column > 7):
            return places


        i = row - 1
        if (i >= 0 and self.board[i][column] == other):
            i = i - 1
            while (i >= 0 and self.board[i][column] == other):
                i = i - 1
            if (i >= 0 and self.board[i][column] == 0):
                places = places + [(i, column)]

        i = row - 1
        j = column + 1
        if (i >= 0 and j < 8 and self.board[i][j] == other):
            i = i - 1
            j = j + 1
            while (i >= 0 and j < 8 and self.board[i][j] == other):
                i = i - 1
                j = j + 1
            if (i >= 0 and j < 8 and self.board[i][j] == 0):
                places = places + [(i, j)]

        j = column + 1
        if (j < 8 and self.board[row][j] == other):
            j = j + 1
            while (j < 8 and self.board[row][j] == other):
                j = j + 1
            if (j < 8 and self.board[row][j] == 0):
                places = places + [(row, j)]

        i = row + 1
        j = column + 1
        if (i < 8 and j < 8 and self.board[i][j] == other):
            i = i + 1
            j = j + 1
            while (i < 8 and j < 8 and self.board[i][j] == other):
                i = i + 1
                j = j + 1
            if (i < 8 and j < 8 and self.board[i][j] == 0):
                places = places + [(i, j)]

        i = row + 1
        if (i < 8 and self.board[i][column] == other):
            i = i + 1
            while (i < 8 and self.board[i][column] == other):
                i = i + 1
            if (i < 8 and self.board[i][column] == 0):
                places = places + [(i, column)]

        i = row + 1
        j = column - 1
        if (i < 8 and j >= 0 and self.board[i][j] == other):
            i = i + 1
            j = j - 1
            while (i < 8 and j >= 0 and self.board[i][j] == other):
                i = i + 1
                j = j - 1
            if (i < 8 and j >= 0 and self.board[i][j] == 0):
                places = places + [(i, j)]

        j = column - 1
        if (j >= 0 and self.board[row][j] == other):
            j = j - 1
            while (j >= 0 and self.board[row][j] == other):
                j = j - 1
            if (j >= 0 and self.board[row][j] == 0):
                places = places + [(row, j)]

        i = row - 1
        j = column - 1
        if (i >= 0 and j >= 0 and self.board[i][j] == other):
            i = i - 1
            j = j - 1
            while (i >= 0 and j >= 0 and self.board[i][j] == other):
                i = i - 1
                j = j - 1
            if (i >= 0 and j >= 0 and self.board[i][j] == 0):
                places = places + [(i, j)]

        return places

    def get_valid_moves(self, color):

        if color == BLACK:
            other = WHITE
        else:
            other = BLACK

        places = []

        for i in range(8):
            for j in range(8):
                if self.board[i][j] == color:
                    places = places + self.lookup(i, j, color)

        places = list(set(places))
        self.valid_moves = places
        return places

    def apply_move(self, move, color):

        if move in self.valid_moves:
            self.board[move[0]][move[1]] = color
            for i in range(1, 9):
                self.flip(i, move, color)

    def flip(self, direction, position, color):

        if direction == 1:
            row_inc = -1
            col_inc = 0
        elif direction == 2:
            row_inc = -1
            col_inc = 1
        elif direction == 3:
            row_inc = 0
            col_inc = 1
        elif direction == 4:
            row_inc = 1
            col_inc = 1
        elif direction == 5:
            row_inc = 1
            col_inc = 0
        elif direction == 6:
            row_inc = 1
            col_inc = -1
        elif direction == 7:
            row_inc = 0
            col_inc = -1
        elif direction == 8:
            row_inc = -1
            col_inc = -1

        places = []    
        i = position[0] + row_inc
        j = position[1] + col_inc

        if color == WHITE:
            other = BLACK
        else:
            other = WHITE

        if i in range(8) and j in range(8) and self.board[i][j] == other:
            places = places + [(i, j)]
            i = i + row_inc
            j = j + col_inc
            while i in range(8) and j in range(8) and self.board[i][j] == other:
                places = places + [(i, j)]
                i = i + row_inc
                j = j + col_inc
            if i in range(8) and j in range(8) and self.board[i][j] == color:
                for pos in places:
                    self.board[pos[0]][pos[1]] = color

    def get_changes(self):

        whites, blacks, empty = self.count_stones()

        return (self.board, blacks, whites)

    def game_ended(self):
        whites, blacks, empty = self.count_stones()
        if whites == 0 or blacks == 0 or empty == 0:
            return True

        if self.get_valid_moves(BLACK) == [] and \
        self.get_valid_moves(WHITE) == []:
            return True

        return False

    def print_board(self):
        for i in range(8):
            print(i, ' |', end=' ')
            for j in range(8):
                if self.board[i][j] == BLACK:
                    print('B', end=' ')
                elif self.board[i][j] == WHITE:
                    print('W', end=' ')
                else:
                    print(' ', end=' ')
                print('|', end=' ')
            print()

    def count_stones(self):

        whites = 0
        blacks = 0
        empty = 0
        for i in range(8):
            for j in range(8):
                if self.board[i][j] == WHITE:
                    whites += 1
                elif self.board[i][j] == BLACK:
                    blacks += 1
                else:
                    empty += 1
        return whites, blacks, empty

    def compare(self, otherBoard):

        diffBoard = Board()
        diffBoard.board[3][4] = 0
        diffBoard.board[3][3] = 0
        diffBoard.board[4][3] = 0
        diffBoard.board[4][4] = 0
        for i in range(8):
            for j in range(8):
                if otherBoard.board[i][j] != self.board[i][j]:
                    diffBoard.board[i][j] = otherBoard.board[i][j]
        return otherBoard

    def get_adjacent_count(self, color):

        adjCount = 0
        for x, y in [(a, b) for a in range(8) for b in range(8) if self.board[a][b] == color]:
            for i, j in [(a, b) for a in [-1, 0, 1] for b in [-1, 0, 1]]:
                if 0 <= x + i <= 7 and 0 <= y + j <= 7:
                    if self.board[x + i][y + j] == EMPTY:
                        adjCount += 1
        return adjCount

    def next_states(self, color):

        valid_moves = self.get_valid_moves(color)
        for move in valid_moves:
            newBoard = deepcopy(self)
            newBoard.apply_move(move, color)
            yield newBoard
