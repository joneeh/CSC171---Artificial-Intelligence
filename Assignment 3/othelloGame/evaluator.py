EMPTY = 0
BLACK = 1
WHITE = 2


class Evaluator(object):
    WIPEOUT_SCORE = 1000 
    PIECE_COUNT_WEIGHT = [0, 0, 0, 4, 1]
    POTENTIAL_MOBILITY_WEIGHT = [5, 4, 3, 2, 0]
    MOBILITY_WEIGHT = [7, 6, 5, 4, 0]
    CORNER_WEIGHT = [35, 35, 35, 35, 0]
    EDGE_WEIGHT = [0, 3, 4, 5, 0]
    XSQUARE_WEIGHT = [-8, -8, -8, -8, 0]

    def get_piece_differential(self, deltaBoard, band):
        if Evaluator.PIECE_COUNT_WEIGHT[band] != 0:
            whites, blacks, empty = deltaBoard.count_stones()
            if self.player == WHITE:
                myScore = whites
                yourScore = blacks
            else:
                myScore = blacks
                yourScore = whites
            return Evaluator.PIECE_COUNT_WEIGHT[band] * (myScore - yourScore)
        return 0

    def get_corner_differential(self, deltaCount, deltaBoard, band):
        if Evaluator.CORNER_WEIGHT[band] != 0:
            myScore = 0
            yourScore = 0
            for i in [0, 7]:
                for j in [0, 7]:
                    if deltaBoard.board[i][j] == self.player:
                        myScore += 1
                    elif deltaBoard.board[i][j] == self.enemy:
                        yourScore += 1
                    if myScore + yourScore >= deltaCount:
                        break
                if myScore + yourScore >= deltaCount:
                    break
            return Evaluator.CORNER_WEIGHT[band] * (myScore - yourScore)
        return 0

    def get_edge_differential(self, deltaCount, deltaBoard, band):

        if Evaluator.EDGE_WEIGHT[band] != 0:
            myScore = 0
            yourScore = 0
            squares = [(a, b) for a in [0, 7] for b in range(1, 7)] \
                + [(a, b) for a in range(1, 7) for b in [0, 7]]
            for x, y in squares:
                if deltaBoard.board[x][y] == self.player:
                    myScore += 1
                elif deltaBoard.board[x][y] == self.enemy:
                    yourScore += 1
                if myScore + yourScore >= deltaCount:
                    break
            return Evaluator.EDGE_WEIGHT[band] * (myScore - yourScore)
        return 0

    def get_xsquare_differential(self, startBoard, currentBoard, deltaBoard, band):

        if Evaluator.XSQUARE_WEIGHT[band] != 0:
            myScore = 0
            yourScore = 0
            for x, y in [(a, b) for a in [1, 6] for b in [1, 6]]:
                if deltaBoard.board[x][y] != EMPTY and startBoard.board[x][y] == EMPTY:
                    cornerx = x
                    cornery = y
                    if cornerx == 1:
                        cornerx = 0
                    elif cornerx == 6:
                        cornerx = 7
                    if cornery == 1:
                        cornery = 0
                    elif cornery == 6:
                        cornery = 7
                    if currentBoard.board[cornerx][cornery] == EMPTY:
                        if currentBoard.board[x][y] == self.player:
                            myScore += 1
                        elif currentBoard.board[x][y] == self.enemy:
                            yourScore += 1
            return Evaluator.XSQUARE_WEIGHT[band] * (myScore - yourScore)
        return 0

    def get_potential_mobility_differential(self, startBoard, currentBoard, band):

        if Evaluator.POTENTIAL_MOBILITY_WEIGHT[band] != 0:
            myScore = currentBoard.get_adjacent_count(
                self.enemy) - startBoard.get_adjacent_count(self.enemy)
            yourScore = currentBoard.get_adjacent_count(
                self.player) - startBoard.get_adjacent_count(self.player)
            return Evaluator.POTENTIAL_MOBILITY_WEIGHT[band] * (myScore - yourScore)
        return 0

    def get_mobility_differential(self, startBoard, currentBoard, band):

        myScore = len(currentBoard.get_valid_moves(self.player)) - \
            len(startBoard.get_valid_moves(self.player))
        yourScore = len(currentBoard.get_valid_moves(
            self.enemy)) - len(startBoard.get_valid_moves(self.enemy))
        return Evaluator.MOBILITY_WEIGHT[band] * (myScore - yourScore)

    def score(self, startBoard, board, currentDepth, player, opponent):

        self.player = player
        self.enemy = opponent
        sc = 0
        whites, blacks, empty = board.count_stones()
        deltaBoard = board.compare(startBoard)
        deltaCount = sum(deltaBoard.count_stones())

        if (self.player == WHITE and whites == 0) or (self.player == BLACK and blacks == 0):
            return -Evaluator.WIPEOUT_SCORE
        if (self.enemy == WHITE and whites == 0) or (self.enemy == BLACK and blacks == 0):
            return Evaluator.WIPEOUT_SCORE

        piece_count = whites + blacks
        band = 0
        if piece_count <= 16:
            band = 0
        elif piece_count <= 32:
            band = 1
        elif piece_count <= 48:
            band = 2
        elif piece_count <= 64 - currentDepth:
            band = 3
        else:
            band = 4

        sc += self.get_piece_differential(deltaBoard, band)
        sc += self.get_corner_differential(deltaCount, deltaBoard, band)
        sc += self.get_edge_differential(deltaCount, deltaBoard, band)
        sc += self.get_xsquare_differential(startBoard,
                                            board, deltaBoard, band)
        sc += self.get_potential_mobility_differential(startBoard, board, band)
        sc += self.get_mobility_differential(startBoard, board, band)
        return sc
